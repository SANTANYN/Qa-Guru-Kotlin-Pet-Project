package frontend.components

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.`$`
import io.qameta.allure.Step

/**
 * Компонент главного изображения на домашней странице.
 */
class MainImageComponent {

    private val mainImage = `$`("[data-test-id=\"main-image\"]")
    private val mainImageText = `$`("[data-test-id=\"main-image-text\"]")

    @Step("Проверить видимость главного изображения")
    fun shouldBeVisible() {
        mainImage.shouldBe(Condition.visible)
    }

    @Step("Проверить текст на главном изображении: {expectedText}")
    fun shouldHaveText(expectedText: String) {
        mainImageText.shouldHave(text(expectedText))
    }
}
