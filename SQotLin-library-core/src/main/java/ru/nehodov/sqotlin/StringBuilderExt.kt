package ru.nehodov.sqotlin

    fun StringBuilder.appendIf(condition: Boolean, vararg value: Any?) {
            if (condition) {
                this.append(value)
            }
    }

    fun StringBuilder.appendIf(condition: Boolean, vararg value: String?) {
        if (condition) {
            this.append(value)
        }
    }

    fun StringBuilder.appendIf(condition: Boolean, vararg value: CharSequence?) {
        if (condition) {
            this.append(value)
        }
    }