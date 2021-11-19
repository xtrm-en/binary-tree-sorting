package me.xtrm.bts

data class BinaryTree(
    private val nodeValue: Int,
    private var leftTree: BinaryTree?,
    private var rightTree: BinaryTree?
) {

    fun search(n: Int): Boolean {
        val value = this.nodeValue
        return if (value == n) true
        else if (value > n) this.leftTree?.search(n) ?: false
        else this.rightTree?.search(n) ?: false
    }

    fun insert(n: Int) {
        val value = this.nodeValue
        if (value > n) {
            if (this.leftTree?.apply { this.insert(n) } != null) {
                this.leftTree = n.asTree()
            }
        } else {
            if (this.rightTree?.apply { this.insert(n) } != null) {
                this.rightTree = n.asTree()
            }
        }
    }

    override fun toString(): String {
        val sb = StringBuilder()
        this.leftTree?.let { sb.append("($it)<-") }
        sb.append(this.nodeValue)
        this.rightTree?.let { sb.append("->($it)") }
        return sb.toString()
    }
}

fun Int.asTree(): BinaryTree = BinaryTree(this, null, null)
fun tree(value: Int, leftTree: BinaryTree?, rightTree: BinaryTree?): BinaryTree = BinaryTree(value, leftTree, rightTree)

fun main() {
    val sampleTree = tree(
        20,
        tree(
            15,
            7.asTree(),
            tree(
                19,
                16.asTree(),
                null
            )
        ),
        tree(
            40,
            null,
            tree(
                60,
                50.asTree(),
                70.asTree()
            )
        )
    )
    println(sampleTree)

    assert(sampleTree.search(70)) { "yes" }
    assert(sampleTree.search(19)) { "yes" }
    assert(sampleTree.search(-1)) { "no" }
}