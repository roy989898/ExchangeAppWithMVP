package poly.pom.exchangerateapp.repository.RetrofitModule;

import java.util.HashMap;

import poly.pom.exchangerateapp.repository.CountryName;

public class Rates {

    private Double AUD;
    private Double BGN;
    private Double BRL;
    private Double CAD;
    private Double CHF;
    private Double CNY;
    private Double CZK;
    private Double DKK;
    private Double GBP;
    private Double HKD;
    private Double HRK;
    private Double HUF;
    private Double IDR;
    private Double ILS;
    private Double INR;
    private Double JPY;
    private Double KRW;
    private Double MXN;
    private Double MYR;
    private Double NOK;
    private Double NZD;
    private Double PHP;
    private Double PLN;
    private Double RON;
    private Double RUB;
    private Double SEK;
    private Double SGD;
    private Double THB;
    private Double TRY;
    private Double USD;
    private Double ZAR;

    public Double getAUD() {
        return AUD;
    }

    public void setAUD(Double AUD) {
        this.AUD = AUD;
    }

    public Double getBGN() {
        return BGN;
    }

    public void setBGN(Double BGN) {
        this.BGN = BGN;
    }

    public Double getHRK() {
        return HRK;
    }

    public void setHRK(Double HRK) {
        this.HRK = HRK;
    }

    public Double getILS() {
        return ILS;
    }

    public void setILS(Double ILS) {
        this.ILS = ILS;
    }

    public Double getCAD() {
        return CAD;
    }

    public void setCAD(Double CAD) {
        this.CAD = CAD;
    }

    public Double getCHF() {
        return CHF;
    }

    public void setCHF(Double CHF) {
        this.CHF = CHF;
    }

    public Double getCNY() {
        return CNY;
    }

    public void setCNY(Double CNY) {
        this.CNY = CNY;
    }

    public Double getCZK() {
        return CZK;
    }

    public void setCZK(Double CZK) {
        this.CZK = CZK;
    }

    public Double getDKK() {
        return DKK;
    }

    public void setDKK(Double DKK) {
        this.DKK = DKK;
    }

    public Double getGBP() {
        return GBP;
    }

    public void setGBP(Double GBP) {
        this.GBP = GBP;
    }

    public Double getHKD() {
        return HKD;
    }

    public void setHKD(Double HKD) {
        this.HKD = HKD;
    }

    public Double getHUF() {
        return HUF;
    }

    public void setHUF(Double HUF) {
        this.HUF = HUF;
    }

    public Double getIDR() {
        return IDR;
    }

    public void setIDR(Double IDR) {
        this.IDR = IDR;
    }

    public Double getBRL() {
        return BRL;
    }

    public void setBRL(Double BRL) {
        this.BRL = BRL;
    }

    public Double getINR() {
        return INR;
    }

    public void setINR(Double INR) {
        this.INR = INR;
    }

    public Double getJPY() {
        return JPY;
    }

    public void setJPY(Double JPY) {
        this.JPY = JPY;
    }

    public Double getKRW() {
        return KRW;
    }

    public void setKRW(Double KRW) {
        this.KRW = KRW;
    }

    public Double getMXN() {
        return MXN;
    }

    public void setMXN(Double MXN) {
        this.MXN = MXN;
    }

    public Double getMYR() {
        return MYR;
    }

    public void setMYR(Double MYR) {
        this.MYR = MYR;
    }

    public Double getNOK() {
        return NOK;
    }

    public void setNOK(Double NOK) {
        this.NOK = NOK;
    }

    public Double getNZD() {
        return NZD;
    }

    public void setNZD(Double NZD) {
        this.NZD = NZD;
    }

    public Double getPHP() {
        return PHP;
    }

    public void setPHP(Double PHP) {
        this.PHP = PHP;
    }

    public Double getPLN() {
        return PLN;
    }

    public void setPLN(Double PLN) {
        this.PLN = PLN;
    }

    public Double getRON() {
        return RON;
    }

    public void setRON(Double RON) {
        this.RON = RON;
    }

    public Double getRUB() {
        return RUB;
    }

    public void setRUB(Double RUB) {
        this.RUB = RUB;
    }

    public Double getSEK() {
        return SEK;
    }

    public void setSEK(Double SEK) {
        this.SEK = SEK;
    }

    public Double getSGD() {
        return SGD;
    }

    public void setSGD(Double SGD) {
        this.SGD = SGD;
    }

    public Double getTHB() {
        return THB;
    }

    public void setTHB(Double THB) {
        this.THB = THB;
    }

    public Double getTRY() {
        return TRY;
    }

    public void setTRY(Double TRY) {
        this.TRY = TRY;
    }

    public Double getUSD() {
        return USD;
    }

    public void setUSD(Double USD) {
        this.USD = USD;
    }

    public Double getZAR() {
        return ZAR;
    }

    public void setZAR(Double ZAR) {
        this.ZAR = ZAR;
    }

    public HashMap<String,Double> getRateMap(){
        HashMap<String,Double> rateMap=new HashMap<>();
        rateMap.put(CountryName.AUD,getAUD());
        rateMap.put(CountryName.BGN,getBGN());
        rateMap.put(CountryName.BRL,getBRL());
        rateMap.put(CountryName.CAD,getCAD());
        rateMap.put(CountryName.CHF,getCHF());
        rateMap.put(CountryName.CNY,getCNY());
        rateMap.put(CountryName.CZK,getCZK());
        rateMap.put(CountryName.DKK,getDKK());
        rateMap.put(CountryName.GBP,getGBP());
        rateMap.put(CountryName.HKD,getHKD());
        rateMap.put(CountryName.HRK,getHRK());
        rateMap.put(CountryName.HUF,getHUF());
        rateMap.put(CountryName.IDR,getIDR());
        rateMap.put(CountryName.ILS,getILS());
        rateMap.put(CountryName.INR,getINR());
        rateMap.put(CountryName.JPY,getJPY());
        rateMap.put(CountryName.KRW,getKRW());
        rateMap.put(CountryName.MXN,getMXN());
        rateMap.put(CountryName.MYR,getMYR());
        rateMap.put(CountryName.NOK,getNOK());
        rateMap.put(CountryName.NZD,getNZD());
        rateMap.put(CountryName.PHP,getPHP());
        rateMap.put(CountryName.PLN,getPLN());
        rateMap.put(CountryName.RON,getRON());
        rateMap.put(CountryName.RUB,getRUB());
        rateMap.put(CountryName.SEK,getSEK());
        rateMap.put(CountryName.SGD,getSGD());
        rateMap.put(CountryName.THB,getTHB());
        rateMap.put(CountryName.TRY,getTRY());
        rateMap.put(CountryName.USD,getUSD());
        rateMap.put(CountryName.ZAR,getZAR());

        return rateMap;


    }
}
