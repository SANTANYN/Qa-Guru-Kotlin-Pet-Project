package frontend.components

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import frontend.extensions.findByOrFail

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
        val element = cardsCollection.findByOrFail(
            text(productName),
            "Карточка товара с именем '$productName' не найдена в контейнере!"
        )
        return ProductCardComponent(element)
    }

    fun getCardByIndex(index: Int): ProductCardComponent {
        return ProductCardComponent(cardsCollection[index])
    }
}
