package frontend.components

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.SelenideElement
import io.qameta.allure.Step

/**
 * Важный принцип: мы принимаем в конструкторе корневой элемент карточки товара.
 * Все внутренние элементы ищем только внутри этого корневого элемента, 
 * обеспечивая инкапсуляцию.
 */
class ProductCardComponent(private val root: SelenideElement) {

    private val name = root.`$`(frontend.helpers.Wrappers.byDataTestGroup("product-card-name"))
    private val description = root.`$`(frontend.helpers.Wrappers.byDataTestGroup("product-card-description"))
    private val price = root.`$`(frontend.helpers.Wrappers.byDataTestGroup("product-card-price"))
    private val incrementBtn = root.`$`(frontend.helpers.Wrappers.byDataTestGroup("product-card-increment"))
    private val decrementBtn = root.`$`(frontend.helpers.Wrappers.byDataTestGroup("product-card-decrement"))
    private val qty = root.`$`(frontend.helpers.Wrappers.byDataTestGroup("product-card-qty"))

    fun getNameText(): String = name.text
    fun getPriceText(): String = price.text
    fun getDescriptionText(): String = description.text
    fun getQuantityText(): String = qty.text

    @Step("Проверить имя товара: {expectedName}")
    fun checkName(expectedName: String) {
        name.shouldHave(text(expectedName))
    }

    @Step("Проверить цену товара: {expectedPrice}")
    fun checkPrice(expectedPrice: String) {
        price.shouldHave(text(expectedPrice))
    }

    @Step("Увеличить количество")
    fun clickIncrement() {
        incrementBtn.click()
    }

    @Step("Уменьшить количество")
    fun clickDecrement() {
        decrementBtn.click()
    }
}
