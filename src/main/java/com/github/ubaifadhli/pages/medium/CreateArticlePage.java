package com.github.ubaifadhli.pages.medium;

import com.github.ubaifadhli.annotations.Locator;
import com.github.ubaifadhli.annotations.WebLocator;
import com.github.ubaifadhli.pages.PageObject;
import com.github.ubaifadhli.util.Element;

public class CreateArticlePage extends PageObject {
    @Locator(webXPath = "//h3[contains(@class, 'graf--title')]",
            mobileID = "com.medium.reader:id/common_edit_post_paragraph_text")
    private Element articleTextArea;

    @WebLocator(xpath = "//p[contains(@class, 'graf--p')]")
    private Element articleParagraphTextArea;

    @Locator(webXPath = "//button[@data-action='show-prepublish']",
            mobileID = "com.medium.reader:id/publish_button")
    private Element publishArticleButton;

    @Locator(webXPath = "//button[@data-action='publish']",
            mobileID = "com.medium.reader:id/edit_post_add_tags_dialog_publish")
    private Element confirmPublishArticleButton;

    public void fillAndPublishArticle(String articleTitle) {
        articleTextArea.waitUntilVisible().click();

        articleTextArea.typeIntoField(articleTitle);

        publishArticleButton.waitUntilClickable().click();

        confirmPublishArticleButton.waitUntilClickable().click();

        // Needs to slow down as the published article list are not updated yet.
        waitFor(2);

        openPage("https://medium.com/me/stories/public");
    }
}