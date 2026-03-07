package frontend.pages

import com.codeborne.selenide.CollectionCondition
import com.codeborne.selenide.Selenide.open
import frontend.components.HeaderComponent
import frontend.components.MainImageComponent
import frontend.components.ProductsContainerComponent
import frontend.components.PopularProductsTitleComponent

class HomeViewPage {

    private val header = HeaderComponent()
    private val mainImage = MainImageComponent()
    private val productsContainer = ProductsContainerComponent()
    private val popularProductsTitle = PopularProductsTitleComponent()

    fun openPage(): HomeViewPage {
        open("/")
        return this
    }

    fun checkPopularProductsTitleText(expectedText: String): HomeViewPage {
        popularProductsTitle.checkText(expectedText)
        return this
    }

    fun checkProductsCount(expectedSize: Int): HomeViewPage {
        productsContainer.getCardsCollection().shouldHave(CollectionCondition.size(expectedSize))
        return this
    }

    fun checkMainImageVisible(): HomeViewPage {
        mainImage.shouldBeVisible()
        return this
    }

    fun checkMainImageText(expectedText: String): HomeViewPage {
        mainImage.shouldHaveText(expectedText)
        return this
    }

    fun header(): HeaderComponent = header
    
    fun products(): ProductsContainerComponent = productsContainer

    fun clickProductsLink(): HomeViewPage {
        header.clickLink("Products")
        return this
    }
}
