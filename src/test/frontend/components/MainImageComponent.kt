package frontend.components

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.`$`

/**
 * Компонент главного изображения на домашней странице.
 */
class MainImageComponent {

    private val mainImage = `$`("[data-test-id=\"main-image\"]")
    private val mainImageText = `$`("[data-test-id=\"main-image-text\"]")

    fun shouldBeVisible() {
        mainImage.shouldBe(Condition.visible)
    }

    fun shouldHaveText(expectedText: String) {
        mainImageText.shouldHave(text(expectedText))
    }
}
