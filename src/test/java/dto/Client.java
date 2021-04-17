package dto;

public class Client {

    private String firm_name;
    private String firm_town;
    private String firm_addr;
    private String firm_mol;
    private boolean firm_is_reg_vat;

    public Client() {

    }

    public Client(String firm_name, String firm_town, String firm_addr, String firm_mol, boolean firm_is_reg_vat) {
        setFirm_name(firm_name);
        setFirm_town(firm_town);
        setFirm_addr(firm_addr);
        setFirm_mol(firm_mol);
        setFirm_is_reg_vat(firm_is_reg_vat);
    }

    /**
     * Set the first name of the Client
     * @param firm_name First Name
     */
    public void setFirm_name(String firm_name) {
        this.firm_name = firm_name;
    }

    /**
     * Set the Firm Town of the Client
     * @param firm_town Firm Town
     */
    public void setFirm_town(String firm_town) {
        this.firm_town = firm_town;
    }

    /**
     * Set the Firm Address of the Client
     * @param firm_addr Firm Address
     */
    public void setFirm_addr(String firm_addr) {
        this.firm_addr = firm_addr;
    }

    /**
     * Set the Firm MOL of the Client
     * @param firm_mol Firm MOL
     */
    public void setFirm_mol(String firm_mol) {
        this.firm_mol = firm_mol;
    }

    /**
     * Set the if the Firm is registered by VAT
     * @param firm_is_reg_vat Registered by VAT
     */
    public void setFirm_is_reg_vat(boolean firm_is_reg_vat) {
        this.firm_is_reg_vat = firm_is_reg_vat;
    }

}
