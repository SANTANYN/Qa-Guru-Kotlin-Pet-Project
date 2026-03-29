package frontend.components

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import io.qameta.allure.Step

/**
 * Компонент главного изображения на домашней странице.
 */
class MainImageComponent {

    private val mainImage = `$`("[data-test-id=\"main-image\"]")
    private val mainImageText = `$`("[data-test-id=\"main-image-text\"]")

    @Step("Получить элемент главного изображения")
    fun getElement() = mainImage

    @Step("Получить текст на главном изображении")
    fun getText(): String = mainImageText.text()

    @Step("Главное изображение отображается")
    fun isDisplayed(): Boolean = mainImage.`is`(visible)
}
