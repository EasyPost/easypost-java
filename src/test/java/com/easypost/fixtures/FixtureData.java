package com.easypost.fixtures;

import com.easypost.fixtures.sections.Addresses;
import com.easypost.fixtures.sections.CarrierAccounts;
import com.easypost.fixtures.sections.CarrierStrings;
import com.easypost.fixtures.sections.CreditCards;
import com.easypost.fixtures.sections.CustomsInfos;
import com.easypost.fixtures.sections.CustomsItems;
import com.easypost.fixtures.sections.FormOptions;
import com.easypost.fixtures.sections.Insurances;
import com.easypost.fixtures.sections.Orders;
import com.easypost.fixtures.sections.PageSizes;
import com.easypost.fixtures.sections.Parcels;
import com.easypost.fixtures.sections.Pickups;
import com.easypost.fixtures.sections.ReportTypes;
import com.easypost.fixtures.sections.ServiceNames;
import com.easypost.fixtures.sections.Shipments;
import com.easypost.fixtures.sections.TaxIdentifiers;
import com.easypost.fixtures.sections.Users;
import com.google.gson.annotations.SerializedName;

public final class FixtureData {

    @SerializedName ("addresses")
    public Addresses addresses;

    @SerializedName ("carrier_accounts")
    public CarrierAccounts carrierAccounts;

    @SerializedName ("carrier_strings")
    public CarrierStrings carrierStrings;

    @SerializedName ("credit_cards")
    public CreditCards creditCards;

    @SerializedName ("customs_infos")
    public CustomsInfos customsInfos;

    @SerializedName ("customs_items")
    public CustomsItems customsItems;

    @SerializedName ("form_options")
    public FormOptions formOptions;

    @SerializedName ("insurances")
    public Insurances insurances;

    @SerializedName ("orders")
    public Orders orders;

    @SerializedName ("page_sizes")
    public PageSizes pageSizes;

    @SerializedName ("parcels")
    public Parcels parcels;

    @SerializedName ("pickups")
    public Pickups pickups;

    @SerializedName ("report_types")
    public ReportTypes reportTypes;

    @SerializedName ("service_names")
    public ServiceNames serviceNames;

    @SerializedName ("shipments")
    public Shipments shipments;

    @SerializedName ("tax_identifiers")
    public TaxIdentifiers taxIdentifiers;

    @SerializedName ("users")
    public Users users;

    @SerializedName ("webhook_url")
    public String webhookUrl;

}
