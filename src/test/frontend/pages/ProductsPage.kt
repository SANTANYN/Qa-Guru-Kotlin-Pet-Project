package frontend.pages

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.`$`

class ProductsPage {

    private val productsTitle = `$`("[data-test-id=\"products-title\"]")

    fun openPage(): ProductsPage {
        com.codeborne.selenide.Selenide.open("/products")
        return this
    }

    fun checkProductsTitleText(expectedText: String): ProductsPage {
        productsTitle.shouldHave(text(expectedText))
        return this
    }
}
