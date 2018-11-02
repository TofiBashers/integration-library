package ru.evotor.framework.counterparties.mapper

import android.os.Bundle
import ru.evotor.framework.counterparties.Counterparty
import java.util.*

internal object CounterpartyMapper {

    private const val KEY_UUID = "UUID"
    private const val KEY_COUNTERPARTY_TYPE = "COUNTERPARTY_TYPE"
    private const val KEY_FULL_NAME = "FULL_NAME"
    private const val KEY_SHORT_NAME = "SHORT_NAME"
    private const val KEY_INN = "INN"
    private const val KEY_KPP = "KPP"
    private const val KEY_CONTACTS = "CONTACTS"
    private const val KEY_PHONES = "PHONES"
    private const val KEY_ADDRESSES = "ADDRESSES"

    fun readUuid(bundle: Bundle?) = bundle?.let { it.getString(KEY_UUID)?.let { uuid -> UUID.fromString(uuid) } }

    fun readCounterpartyType(bundle: Bundle?) = bundle?.let {
        if (it.containsKey(KEY_COUNTERPARTY_TYPE))
            Counterparty.Type.values()[it.getInt(KEY_COUNTERPARTY_TYPE)]
        else
            null
    }

    fun readFullName(bundle: Bundle?) = bundle?.getString(KEY_FULL_NAME)

    fun readShortName(bundle: Bundle?) = bundle?.getString(KEY_SHORT_NAME)

    fun readInn(bundle: Bundle?) = bundle?.getString(KEY_INN)

    fun readKpp(bundle: Bundle?) = bundle?.getString(KEY_KPP)

    fun readContacts(bundle: Bundle?) = getContacts(bundle?.getBundle(KEY_CONTACTS))

    private fun getContacts(bundle: Bundle?) = bundle?.let {
        Counterparty.Contacts(
                phones = it.getStringArrayList(KEY_PHONES),
                addresses = it.getStringArrayList(KEY_ADDRESSES)
        )
    }

    fun write(counterparty: Counterparty) = Bundle().apply {
        this.putString(KEY_UUID, counterparty.uuid?.toString())
        counterparty.counterpartyType?.let { this.putInt(KEY_COUNTERPARTY_TYPE, it.ordinal) }
        this.putString(KEY_FULL_NAME, counterparty.fullName)
        this.putString(KEY_SHORT_NAME, counterparty.shortName)
        this.putString(KEY_INN, counterparty.inn)
        this.putString(KEY_KPP, counterparty.kpp)
        this.putBundle(KEY_CONTACTS, counterparty.contacts?.let { writeContacts(it) })
    }

    private fun writeContacts(contacts: Counterparty.Contacts) = Bundle().apply {
        this.putStringArrayList(KEY_PHONES, contacts.phones as ArrayList<String>?)
        this.putStringArrayList(KEY_ADDRESSES, contacts.addresses as ArrayList<String>?)
    }

    fun <C : Counterparty> convertToNull(counterparty: C): C? =
            if (counterparty.uuid == null &&
                    counterparty.counterpartyType == null &&
                    counterparty.fullName == null &&
                    counterparty.shortName == null &&
                    counterparty.inn == null &&
                    counterparty.kpp == null &&
                    (counterparty.contacts == null ||
                            (counterparty.contacts.phones == null &&
                                    counterparty.contacts.addresses == null)))
                null
            else
                counterparty

}