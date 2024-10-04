package com.bahraoui.first_code.preparation_pour_exam_1

open class Produit(var code: Int = 1, var prix: Double = 0.0) {
    constructor(code: Int) : this(code, 0.0)

    open fun prixProduit(): Double {
        return this.prix
    }

    override fun toString(): String {
        return "${this.code};$prix"
    }

    override fun equals(other: Any?): Boolean {
        if (other is Produit) {
            if (this.code == other.code) {
                return true
            }
        }
        return false

    }

}

class ProduitEnSolde(code: Int, prix: Double, var remise: Double) : Produit(code, prix) {
    init {
        when (remise) {
            !in 0.0..90.0 -> {
                throw Exception("impossible")
            }

        }
    }

    constructor() : this(1, 10.5, 25.00)

    override fun prixProduit(): Double {
//        var rem=(remise / 100)
        var nprix = prix - (prix * (remise / 100))
        return nprix
    }

    override fun toString(): String {
        return "$code;$prix;${this.prixProduit()}"
    }
}

class Boutique(var listProduit: MutableList<Produit>) {
    fun indiceDe(code: Int): Int {
        for (v in listProduit) {
            if (v.code == code) {
                return listProduit.indexOf(v)
            }
        }
        return -1
    }

    fun ajouter(p:Produit){
        if(listProduit.contains(p)){
            throw Exception("produit déjà existe")
        }
        listProduit.add(p)
    }

    fun supprimer(code:Int):Boolean{
        if(indiceDe(code)!=1){
            var i=indiceDe(code)
            listProduit.remove(listProduit[i])
            return true
        }
        return false
    }

    fun supprimer(p:Produit):Boolean{
        if(listProduit.contains(p)){
            listProduit.remove(p)
            return true
        }
        return false
    }



    fun nombreProduitEnSolde():Int{
        var cmp=0
        for(v in listProduit){
            if(v is ProduitEnSolde){
                cmp+=1
            }
        }
        return cmp
    }
}

fun main(){
    val P1=Produit(1,175.65)
    val P2=Produit(2,356.65)
    val P3=Produit(3,200.65)

    val PS1=ProduitEnSolde(4,257.25,25.0)
    val PS2=ProduitEnSolde(5,350.75,50.0)
    val PS3=ProduitEnSolde(6,500.45,35.0)

    val B1=Boutique(mutableListOf())

    B1.ajouter(P1)
    B1.ajouter(P2)
    B1.ajouter(PS1)
    B1.ajouter(PS2)
    B1.ajouter(P3)


    println(P1.toString())
    println(PS1.toString())
    println(PS1.prixProduit())

    println(B1.indiceDe(PS1.code))

    println(B1.supprimer(PS2))
    println(B1.supprimer(P3.code))



    println(B1.nombreProduitEnSolde())

}