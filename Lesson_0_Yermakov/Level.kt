
class Level {
    private val monstersInLevel: ArrayList<Monster>

    init {
        val numOfMonster = (1 ..MAX_MONSTERS_COUNT).random()
        monstersInLevel = ArrayList(numOfMonster)
        for (i in 0 until numOfMonster) {
            val minDamage = (1 ..20).random()
            monstersInLevel.add(Monster("Skeleton", (1 ..30).random(), (1 ..30).random(), (1 ..100).random(), minDamage, minDamage + (1 ..10).random()))
        }
    }

    fun stepIntoRandomFight(hero: Hero) {
        if (monstersInLevel.isEmpty() || !hero.isAlive) {
            return
        }
        val randomIndex = (1..monstersInLevel.size).random()
        val currentEnemy = monstersInLevel.removeAt(randomIndex)
        if (hero.attack - currentEnemy.defence <= 0) {
            println("The last thing you hear before you die is the terrible roar of a monster.\nThis fight was too hard for you")
            return
        }
        while (currentEnemy.isAlive && hero.isAlive) {
            hero.hit(currentEnemy)
            currentEnemy.hit(hero)
        }
        println(if (hero.isAlive) "You won a battle" else "The last thing you hear before you die is the terrible roar of a monster.")
    }

    val isLevelFinished: Boolean
        get() = monstersInLevel.isEmpty()

    fun finish() {
        monstersInLevel.clear()
    }

    companion object {
        private const val MAX_MONSTERS_COUNT = 10
    }
}
