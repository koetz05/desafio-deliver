package br.com.felipe.dtos;

import java.util.Date;


/*DTO Para Exibir Somente os dados necessarios na view do sistema*/
public class BillToPayDTO {

	private String name;
	private Double originalValue;
	private Double corretValue;
	private Number delayDays;
	private Date paymentDate;

	public BillToPayDTO() {
	}

	public BillToPayDTO(String name, Double originalValue, Double corretValue, Number delayDays, Date paymentDate) {
		super();
		this.name = name;
		this.originalValue = originalValue;
		this.corretValue = corretValue;
		this.delayDays = delayDays;
		this.paymentDate = paymentDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getOriginalValue() {
		return originalValue;
	}

	public void setOriginalValue(Double originalValue) {
		this.originalValue = originalValue;
	}

	public Double getCorretValue() {
		return corretValue;
	}

	public void setCorretValue(Double corretValue) {
		this.corretValue = corretValue;
	}

	public Number getDelayDays() {
		return delayDays;
	}

	public void setDelayDays(Number delayDays) {
		this.delayDays = delayDays;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((corretValue == null) ? 0 : corretValue.hashCode());
		result = prime * result + ((delayDays == null) ? 0 : delayDays.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((originalValue == null) ? 0 : originalValue.hashCode());
		result = prime * result + ((paymentDate == null) ? 0 : paymentDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BillToPayDTO other = (BillToPayDTO) obj;
		if (corretValue == null) {
			if (other.corretValue != null)
				return false;
		} else if (!corretValue.equals(other.corretValue))
			return false;
		if (delayDays == null) {
			if (other.delayDays != null)
				return false;
		} else if (!delayDays.equals(other.delayDays))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (originalValue == null) {
			if (other.originalValue != null)
				return false;
		} else if (!originalValue.equals(other.originalValue))
			return false;
		if (paymentDate == null) {
			if (other.paymentDate != null)
				return false;
		} else if (!paymentDate.equals(other.paymentDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BillToPayDTO [name=" + name + ", originalValue=" + originalValue + ", corretValue=" + corretValue
				+ ", delayDays=" + delayDays + ", paymentDate=" + paymentDate + "]";
	}

}
