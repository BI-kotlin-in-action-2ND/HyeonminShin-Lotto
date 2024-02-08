import com.shm.domain.user.controller.UserController.createUser
import com.shm.domain.user.controller.UserController.showUserBoughtLotto
import com.shm.global.view.dismissPage
import com.shm.global.view.welcomePage

fun main(args: Array<String>) {
    welcomePage()

    try {
        val user = createUser()
        user.buyLotto()

        showUserBoughtLotto(user)
    } catch (e: Exception) {
        println(e.message)
    } finally {
        dismissPage()
    }
}
