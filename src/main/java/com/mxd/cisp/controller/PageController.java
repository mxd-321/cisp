package com.mxd.cisp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mxd.cisp.domain.Article;
import com.mxd.cisp.domain.Category;
import com.mxd.cisp.domain.User;
import com.mxd.cisp.service.ArticleService;
import com.mxd.cisp.service.CategoryService;
import com.mxd.cisp.service.CommentService;
import com.mxd.cisp.service.UserService;
import com.mxd.cisp.vo.ArticleVO;
import com.mxd.cisp.vo.CommentVO;

import java.util.Comparator;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author mxd
 * @Date 2021/12/2 20:30
 */
@Controller
public class PageController {

  @Resource
  CategoryService categoryService;

  @Resource
  UserService userService;

  @Resource
  ArticleService articleService;

  @Resource
  CommentService commentService;

  @RequestMapping("/")
  public void test(HttpServletResponse response) throws Exception {
    response.sendRedirect("/index");
  }

  @RequestMapping("/index")
  public String index(HttpServletRequest request, @RequestParam(required = false) Integer cId,
    @RequestParam(required = false) String order, @RequestParam(required = false) String search,
    @RequestParam(required = false) Integer page) {
    int pageIndex = 0;
    if (page != null) {
      pageIndex = (page - 1) * 10;
    }
    // 获取热帖
    List<Article> hotArticles = articleService.getHotArticles();
    // 获取帖子类型
    List<Category> list = categoryService.getAllCategory();
    request.setAttribute("category", list);
    List<ArticleVO> articleList;
    int count = 0;
    if (search == null) {
      if (cId == null || cId == 0) {
        // 获取第pageIndex页的帖子
        articleList = articleService.getIndexArticles(pageIndex);
        // 获取帖子个数
        count = articleService.countIndexArticles();
        // 获取帖子的评论数
        for (ArticleVO articleVO : articleList) {
          articleVO.setCommentCount(commentService.getCommentByArticleId(articleVO.getId()).size());
        }
      } else {
        articleList = articleService.getArticlesByCategoryId(cId, pageIndex);
        count = articleService.countArticleByCateId();
        for (ArticleVO articleVO : articleList) {
          articleVO.setCommentCount(commentService.getCommentByArticleId(articleVO.getId()).size());
        }
      }
      if ("hot".equals(order)) {
        articleList.sort(Comparator.comparing(ArticleVO::getCommentCount).reversed());
      }
      if ("new".equals(order)) {
        articleList.sort(Comparator.comparing(ArticleVO::getCreateTime).reversed());
      }
    } else {
      articleList = articleService.searchArticle(null, null, null, search);
    }
    List<ArticleVO> topArticleList = articleService.getTopArticle();
    if (topArticleList.size() > 0) {
      request.setAttribute("topArticleList", topArticleList);
    }
    request.setAttribute("cId", cId);
    request.setAttribute("order", order);
    request.setAttribute("page", page);
    request.setAttribute("articleList", articleList);
    request.setAttribute("articleCount", count);
    request.setAttribute("hotArticles", hotArticles);
    return "index";
  }

  @RequestMapping("/login")
  public String login() {
    return "frontend/user/login";
  }

  @RequestMapping("/register")
  public String register() {
    return "frontend/user/reg";
  }

  @RequestMapping("/403")
  public String error403() {
    return "403";
  }

  @RequestMapping("/404")
  public String error404() {
    return "404";
  }

  @RequestMapping("/500")
  public String error500() {
    return "500";
  }

  @RequestMapping("/admin")
  public String admin() {
    return "backend/login";
  }

  @RequestMapping("/add")
  public String add(HttpServletRequest request) {
    List<Category> list = categoryService.getAllCategory();
    request.getSession().setAttribute("category", list);
    return "frontend/article/add";
  }

  @RequestMapping("/welcome")
  public String welcome(HttpServletRequest request) {
    int userCount = userService.countAll(null);
    int articleCount = articleService.countAllArticle();
    int commentCount = commentService.countAllComment();
    request.setAttribute("userCount", userCount);
    request.setAttribute("articleCount", articleCount);
    request.setAttribute("commentCount", commentCount);
    request.setAttribute("ip", request.getRemoteAddr());
    return "backend/welcome";
  }

  @RequestMapping("/admin/index")
  public String adminIndex() {
    return "backend/index";
  }

  @RequestMapping("/admin/user/userList")
  public String userManager(HttpServletRequest request, String search) {
    int userCount = userService.countAll(search);
    List<User> userList = userService.getUsers(search);
    request.getSession().setAttribute("userCount", userCount);
    request.getSession().setAttribute("userList", userList);
    return "backend/user/list";
  }

  @RequestMapping("/user/set")
  public String userSet() {
    return "frontend/user/set";
  }

  @RequestMapping("/article/{id}")
  public String articleDetails(HttpServletRequest request,
                               @PathVariable(value = "id") Integer id) {
    request.getSession().removeAttribute("commentVOList");
    ArticleVO articleVO = articleService.getArticleById(id);
    List<CommentVO> commentVOList = commentService.getCommentByArticleId(id);
    request.getSession().setAttribute("article", articleVO);
    if (commentVOList.size() != 0) {
      request.getSession().setAttribute("commentVOList", commentVOList);
    }
    return "/frontend/article/detail";
  }

  /**
   * 用户的个人中心
   * @param request
   * @return
   */
  @RequestMapping("/user/center")
  public String userCenter(HttpServletRequest request) {
    // 获取当前用户
    User user = (User) request.getSession().getAttribute("user");
    List<ArticleVO> myArticles = articleService.getArticlesByUserId(user.getId());
    for (ArticleVO articleVO : myArticles) {
      articleVO.setCommentCount(commentService.getCommentByArticleId(articleVO.getId()).size());
    }
    request.getSession().setAttribute("myArticles", myArticles);
    return "frontend/user/center";
  }

  /**
   * 公告
   * @return
   */
  @RequestMapping("/notice")
  public String notice() {
    return "/notice";
  }

  @RequestMapping("/admin/article/articleList")
  public String articleList(HttpServletRequest request,
                            @RequestBody(required = false) String param) {
    List<Category> list = categoryService.getAllCategory();
    request.setAttribute("category", list);
    request.setAttribute("articleCount", articleService.countAllArticle());
    if (param != null) {
      JSONObject json = JSON.parseObject(param);
      Integer categoryId = json.getInteger("categoryId");
      String keyword = json.getString("keyword");
      String startTime = json.getString("startTime");
      String endTime = json.getString("endTime");
      List<ArticleVO> articleVOList = articleService
        .searchArticle(categoryId, startTime, endTime, keyword);
      request.getSession().setAttribute("articleList", articleVOList);
      request.setAttribute("articleCount", articleVOList.size());
      return "/backend/article/list";
    }
    return "/backend/article/list";
  }

  @RequestMapping("/admin/article/category")
  public String categoryList(HttpServletRequest request) {
    List<Category> categoryList = categoryService.getAllCategory();
    request.getSession().setAttribute("categoryList", categoryList);
    return "/backend/article/category";
  }

  @RequestMapping("/admin/article/category/add")
  public String addCategory(HttpServletRequest request) {
    return "/backend/article/category-add";
  }

  @RequestMapping("/admin/article/category/edit")
  public String editCategory(HttpServletRequest request) {
    return "/backend/article/category-edit";
  }

  @RequestMapping("/user/{id}")
  public String userHome(HttpServletRequest request, @PathVariable(value = "id") Integer id) {
    List<ArticleVO> articleVOList = articleService.getArticlesByUserId(id);
    for (ArticleVO articleVO : articleVOList) {
      articleVO.setCommentCount(commentService.getCommentByArticleId(articleVO.getId()).size());
    }
    User userInfo = userService.getUserById(id);
    request.getSession().setAttribute("userInfo", userInfo);
    request.getSession().setAttribute("articleVOList", articleVOList);
    return "/frontend/user/home";
  }

  @RequestMapping("/admin/user/edit")
  public String userEdit(HttpServletRequest request, @RequestParam Integer id) {
    User user = userService.getUserById(id);
    request.setAttribute("editUser", user);
    return "/backend/user/edit";
  }

  @RequestMapping("/admin/user/add")
  public String userAdd(HttpServletRequest request) {
    return "/backend/user/add";
  }

  @RequestMapping("/admin/article/edit")
  public String articleAdd(HttpServletRequest request,
    @RequestParam(required = false) Integer articleId) {
    List<Category> list = categoryService.getAllCategory();
    request.getSession().setAttribute("category", list);
    if (articleId != null) {
      Article article = articleService.getArticleById(articleId);
      request.setAttribute("editArticle", article);
    }
    return "/backend/article/article-edit";
  }

}
