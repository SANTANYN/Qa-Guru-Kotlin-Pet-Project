package frontend.components

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.SelenideElement

/**
 * Важный принцип: мы принимаем в конструкторе корневой элемент карточки товара.
 * Все внутренние элементы ищем только внутри этого корневого элемента, 
 * обеспечивая инкапсуляцию.
 */
class ProductCardComponent(private val root: SelenideElement) {

    private val name = root.`$`("[data-test-group=\"product-card-name\"]")
    private val description = root.`$`("[data-test-group=\"product-card-description\"]")
    private val price = root.`$`("[data-test-group=\"product-card-price\"]")
    private val incrementBtn = root.`$`("[data-test-group=\"product-card-increment\"]")
    private val decrementBtn = root.`$`("[data-test-group=\"product-card-decrement\"]")
    private val qty = root.`$`("[data-test-group=\"product-card-qty\"]")

    fun getNameText(): String = name.text
    fun getPriceText(): String = price.text
    fun getDescriptionText(): String = description.text
    fun getQuantityText(): String = qty.text

    fun checkName(expectedName: String) {
        name.shouldHave(text(expectedName))
    }

    fun checkPrice(expectedPrice: String) {
        price.shouldHave(text(expectedPrice))
    }

    fun clickIncrement() {
        incrementBtn.click()
    }

    fun clickDecrement() {
        decrementBtn.click()
    }
}
