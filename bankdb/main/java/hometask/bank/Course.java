package hometask.bank;


import javax.persistence.*;

@Entity
@Table(name="Courses")
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "from_currency")
    private String fromCurrency;

    @Column(name = "to_currency")
    private String toCurrency;

    private Double coefficient;

    public Course() {
    }
    public Course(String fromCurrency, String toCurrency, Double coefficient) {
        this.setFromCurrency(fromCurrency);
        this.setToCurrency(toCurrency);
        this.coefficient = coefficient;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFromCurrency() {
        return fromCurrency;
    }
    public void setFromCurrency(String from) {
        if(CurrencyTypes.isType(from));
        this.fromCurrency = from;
    }
    public String getToCurrency() {
        return toCurrency;
    }
    public void setToCurrency(String to) {
        if(CurrencyTypes.isType(to));
        this.toCurrency = to;
    }
    public Double getCoefficient() {
        return coefficient;
    }
    public void setCoefficient(Double coeficient) {
        this.coefficient = coeficient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (id != null ? !id.equals(course.id) : course.id != null) return false;
        if (fromCurrency != null ? !fromCurrency.equals(course.fromCurrency) : course.fromCurrency != null)
            return false;
        if (toCurrency != null ? !toCurrency.equals(course.toCurrency) : course.toCurrency != null) return false;
        return coefficient != null ? coefficient.equals(course.coefficient) : course.coefficient == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fromCurrency != null ? fromCurrency.hashCode() : 0);
        result = 31 * result + (toCurrency != null ? toCurrency.hashCode() : 0);
        result = 31 * result + (coefficient != null ? coefficient.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Course{ id=").append(id).append(", from=")
                .append(fromCurrency).append(", to=").append(toCurrency).append(", coeficient=")
                .append(coefficient).append(" }").toString();
    }
}
