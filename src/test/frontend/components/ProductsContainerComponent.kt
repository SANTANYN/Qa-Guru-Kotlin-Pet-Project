package frontend.components

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement

/**
 * Контейнер для карточек товаров.
 * Возвращает дочерние компоненты-карточки (ProductCardComponent)
 */
class ProductsContainerComponent {

    private val container = `$`(".products-container")
    private val cardsCollection = container.`$$`("[data-test-group=\"product-card\"]")

    fun getCardsCollection() = cardsCollection

    fun getAllCards(): List<ProductCardComponent> {
        return cardsCollection.map { ProductCardComponent(it) }
    }

    fun getCardByName(productName: String): ProductCardComponent {
        val element = cardsCollection.findBy(text(productName))
        return ProductCardComponent(element)
    }

    fun getCardByIndex(index: Int): ProductCardComponent {
        return ProductCardComponent(cardsCollection[index])
    }
}
