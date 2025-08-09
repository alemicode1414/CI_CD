package com.example.ci_cd


data class Customer(
    val name: String,
    val email: String
)

class Training {

    fun getPositiveTransaction() {
        val customers = mutableListOf<Customer>()
        val emails = mutableListOf<String>()

        for (c in customers) {
            if (c.email != null) {
                val emailTrim = c.email.trim()
                if (emailTrim.isNotEmpty()) {
                    emails.add(emailTrim)
                }
            }
        }
    }

    fun getPositiveTransaction2() {
        val customers = mutableListOf<Customer>()
        val emails = mutableListOf<String>()

        val x = customers.mapNotNull {
            it.email.trim()
        }.filter { it.isNotEmpty() }

    }
}

