class Hero(name: String?, attack: Int, defence: Int, maxHealth: Int, minDamage: Int, maxDamage: Int) : Entity(name!!, attack, defence, maxHealth, minDamage, maxDamage) {
    private var healCount = MAX_HEAL
    fun heal(): Boolean {
        if (healCount-- != 0) {
            val maxHeal = (0.3 * maxHealth).toInt()
            val healAmount = (1 ..maxHeal).random() + 1
            setHealth(getHealth() + healAmount)
            return true
        }
        return false
    }

    fun printStats() {
        println(if (isAlive) "You, brave " + name + ", have " + getHealth() + " health points left" else "This dungeon has swallowed you, no one will find your bones")
    }

    companion object {
        private const val MAX_HEAL = 4
    }
}
