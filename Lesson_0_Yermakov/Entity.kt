import java.util.*
import kotlin.math.min

abstract class Entity(name: String, attack: Int, defence: Int, maxHealth: Int, minDamage: Int, maxDamage: Int) {
    protected val name: String
    val attack: Int
    val defence: Int
    private var health: Int
    val maxHealth: Int
    private val damage: IntArray

    init {
        var minDamage = minDamage
        require(!(attack < 0 || attack > 30 || defence < 0 || defence > 30 || minDamage >= maxDamage)) { "Bad stats for Entity!" }
        this.name = name
        this.attack = attack
        this.defence = defence
        health = maxHealth
        this.maxHealth = maxHealth
        damage = IntArray(maxDamage - minDamage + 1)
        for (i in damage.indices) {
            damage[i] = minDamage++
        }
    }

    private fun takeDamage(damage: Int) {
        require(damage >= 0) { "Bad damage value!" }
        health = min((health - damage).toDouble(), 0.0).toInt()
    }

    fun getHealth(): Int {
        return health
    }

    fun setHealth(newHealth: Int) {
        require(newHealth > 0) { "Bad health value!" }
        health = min(newHealth.toDouble(), maxHealth.toDouble()).toInt()
    }

    val isAlive: Boolean
        get() = health > 0

    fun hit(enemy: Entity) {
        if (!enemy.isAlive) {
            return
        }
        val attackMod = attack - enemy.defence
        for (i in 0 until attackMod) {
            val diceResult = random.nextInt(1, 7)
            if (diceResult == 5 || diceResult == 6) {
                enemy.takeDamage(damage[random.nextInt(damage.size)])
                break
            }
        }
    }

    companion object {
        private val random = Random()
    }
}
