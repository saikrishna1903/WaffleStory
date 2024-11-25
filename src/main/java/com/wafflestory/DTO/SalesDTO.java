package com.wafflestory.DTO;

public class SalesDTO {

	private Double daySales;
    private Double weeklySales;
    private Double monthlySales;
    private Double yearlySales;

    public SalesDTO(Double daySales, Double weeklySales, Double monthlySales, Double yearlySales) {
        this.daySales = daySales;
        this.weeklySales = weeklySales;
        this.monthlySales = monthlySales;
        this.yearlySales = yearlySales;
    }

    // Getters and Setters

    public Double getDaySales() {
        return daySales;
    }

    public void setDaySales(Double daySales) {
        this.daySales = daySales;
    }

    public Double getWeeklySales() {
        return weeklySales;
    }

    public void setWeeklySales(Double weeklySales) {
        this.weeklySales = weeklySales;
    }

    public Double getMonthlySales() {
        return monthlySales;
    }

    public void setMonthlySales(Double monthlySales) {
        this.monthlySales = monthlySales;
    }

    public Double getYearlySales() {
        return yearlySales;
    }

    public void setYearlySales(Double yearlySales) {
        this.yearlySales = yearlySales;
    }
}
