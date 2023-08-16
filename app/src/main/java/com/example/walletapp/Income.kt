package com.example.walletapp

class Income {

    var date:String = ""
    var amount:String = ""
    var description:String = ""
    var rec_id:String = ""


    constructor(date: String, amount: String, description: String, rec_id: String) {
        this.date = date
        this.amount = amount
        this.description = description
        this.rec_id = rec_id
    }

    constructor()
}