package br.com.felipe.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class BillToPay {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private Double originalValue;
	@Column(nullable = false)
	private Double corretValue;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date paymentDate;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dueDate;
	@Column(nullable = false)
	private Number delayDays;

	public BillToPay() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Number getDelayDays() {
		return delayDays;
	}

	public void setDelayDays(Number delayDays) {
		this.delayDays = delayDays;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((corretValue == null) ? 0 : corretValue.hashCode());
		result = prime * result + ((delayDays == null) ? 0 : delayDays.hashCode());
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		BillToPay other = (BillToPay) obj;
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
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		return "BillToPay [id=" + id + ", name=" + name + ", originalValue=" + originalValue + ", corretValue="
				+ corretValue + ", paymentDate=" + paymentDate + ", dueDate=" + dueDate + ", delayDays=" + delayDays
				+ "]";
	}

}
