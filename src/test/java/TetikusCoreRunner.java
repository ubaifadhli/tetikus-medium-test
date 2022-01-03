import com.github.ubaifadhli.pages.medium.ArticlePage;
import com.github.ubaifadhli.pages.medium.CreateArticlePage;
import com.github.ubaifadhli.pages.medium.HomePage;
import com.github.ubaifadhli.pages.medium.LoginPage;
import com.github.ubaifadhli.runners.TetikusBaseRunner;
import com.github.ubaifadhli.util.RandomGenerator;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class TetikusCoreRunner extends TetikusBaseRunner {
    private HomePage homePage;
    private LoginPage loginPage;
    private CreateArticlePage createArticlePage;
    private ArticlePage articlePage;

    @Test
    public void login() {
        homePage.openPage();
        homePage.goToTwitterLoginPage();

        loginPage.fillTwitterLoginCredentials("", "");
    }

    @Test
    public void searchForArticle() {
        String SEARCH_KEYWORD = "spring boot";
        String EXPECTED_FIRST_ARTICLE_NAME = "How to scale Microservices with Message Queues, Spring Boot, and Kubernetes";

        login();

        homePage.searchForArticle(SEARCH_KEYWORD);

        assertThat(homePage.getFirstArticleTitle(), equalTo(EXPECTED_FIRST_ARTICLE_NAME));
    }

    @Test
    public void createNewArticle() {
        String ARTICLE_TITLE = "Test article " + RandomGenerator.generateString();

        login();

        homePage.createNewArticle();
        createArticlePage.fillAndPublishArticle(ARTICLE_TITLE);

        homePage.refreshProfilePage();

        assertThat(homePage.getFirstUserArticleTitle(), equalTo(ARTICLE_TITLE));
    }

    @Test
    public void createNewComment() {
        String COMMENT = "Try to comment " + RandomGenerator.generateString();

        login();

        homePage.openFirstUserArticle();

        articlePage.createComment(COMMENT);

        assertThat(articlePage.getFirstCommentText(), equalTo(COMMENT));
    }

    @Test
    public void deleteArticle() {
        createNewArticle();

        String firstArticleTitle = homePage.getFirstUserArticleTitle();

        homePage.deleteArticle();

        String currentFirstArticleTitle = homePage.getFirstUserArticleTitle();

        assertThat(firstArticleTitle, not(currentFirstArticleTitle));
    }


}