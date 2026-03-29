package frontend.pages

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.open
import frontend.components.HeaderComponent
import frontend.helpers.Wrappers.Companion.byDataTestId
import frontend.components.MainImageComponent
import frontend.components.list.ProductsContainerComponent
import frontend.components.PopularProductsTitleComponent
import frontend.components.list.ProductItemsList
import frontend.components.popup.CreateUserPopup
import frontend.components.popup.LoginPopup
import io.qameta.allure.Step

class HomeViewPage : BasePage() {

    @Step("Открыть главную страницу")
    fun openPage(): HomeViewPage {
        open("/")
        return this
    }

    @Step("Получить заголовок популярных товаров")
    fun getPopularProductsTitleText(): String = PopularProductsTitleComponent().getText()

    @Step("Получить количество товаров")
    fun getProductsCount(): Int = ProductsContainerComponent().getCardsCollection().size()

    @Step("Получить текст на главном изображении")
    fun getMainImageText(): String = MainImageComponent().getElement().text()

    fun mainImage(): MainImageComponent = MainImageComponent()

    @Step("Перейти к компоненту шапки")
    fun navigateToHeader(): HeaderComponent = HeaderComponent()

    @Step("Нажать Join в шапке")
    fun clickJoin(): CreateUserPopup = navigateToHeader().clickJoin()

    /**
     * Открывает диалог логина.
     *
     * В текущем UI кнопка **Join** сначала открывает регистрацию; вход — по ссылке «your account».
     * Этот метод прячет эту цепочку, чтобы в тестах был сценарий как `openLoginForm().authPopup()...`.
     */
    @Step("Открыть форму логина")
    fun openLoginForm(): HomeViewPage {
        navigateToHeader().clickJoin()
        CreateUserPopup().clickLoginLink()
        return this
    }

    @Step("Попап авторизации")
    fun authPopup(): LoginPopup = LoginPopup()

    fun products(): ProductsContainerComponent = ProductsContainerComponent()

    fun popularItems(): ProductItemsList = ProductItemsList()

    @Step("Получить список популярных товаров")
    fun getPopularProducts(): List<frontend.components.list.ProductItem> = popularItems().getItems()

    @Step("Нажать на ссылку Products в хедере")
    fun clickProductsLink(): HomeViewPage {
        navigateToHeader().clickNavLink(HeaderComponent.NavLink.PRODUCTS)
        return this
    }

    @Step("Кнопка Checkout в открытой панели корзины отображается")
    fun isCartCheckoutButtonVisible(): Boolean = `$`(byDataTestId("cart-checkout")).`is`(visible)
}
