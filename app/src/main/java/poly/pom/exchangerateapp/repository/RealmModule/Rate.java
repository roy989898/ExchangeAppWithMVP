package poly.pom.exchangerateapp.repository.RealmModule;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Rate extends RealmObject {
    @PrimaryKey
    private String name;
    private double rateBaseEur;
    private int updateDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRateBaseEur() {
        return rateBaseEur;
    }

    public void setRateBaseEur(double rateBaseEur) {
        this.rateBaseEur = rateBaseEur;
    }

    public int getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(int updateDate) {
        this.updateDate = updateDate;
    }
}
