package com.easypost.fixtures;

import com.easypost.fixtures.components.Addresses;
import com.easypost.fixtures.components.Billing;
import com.easypost.fixtures.components.CarrierAccounts;
import com.easypost.fixtures.components.CarrierStrings;
import com.easypost.fixtures.components.Claims;
import com.easypost.fixtures.components.CreditCards;
import com.easypost.fixtures.components.CustomsInfos;
import com.easypost.fixtures.components.CustomsItems;
import com.easypost.fixtures.components.FormOptions;
import com.easypost.fixtures.components.Insurances;
import com.easypost.fixtures.components.Luma;
import com.easypost.fixtures.components.Orders;
import com.easypost.fixtures.components.PageSizes;
import com.easypost.fixtures.components.Parcels;
import com.easypost.fixtures.components.Pickups;
import com.easypost.fixtures.components.ReportTypes;
import com.easypost.fixtures.components.ServiceNames;
import com.easypost.fixtures.components.Shipments;
import com.easypost.fixtures.components.TaxIdentifiers;
import com.easypost.fixtures.components.Users;
import com.easypost.fixtures.components.Webhooks;
import com.google.gson.annotations.SerializedName;

public final class FixtureStructure {

    @SerializedName ("addresses")
    public Addresses addresses;

    @SerializedName ("billing")
    public Billing billing;

    @SerializedName ("carrier_accounts")
    public CarrierAccounts carrierAccounts;

    @SerializedName ("carrier_strings")
    public CarrierStrings carrierStrings;

    @SerializedName ("claims")
    public Claims claims;

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

    @SerializedName ("luma")
    public Luma luma;

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

    @SerializedName ("webhooks")
    public Webhooks webhooks;

}
