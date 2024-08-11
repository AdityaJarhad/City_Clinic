package com.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Doctor extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false) // optional BUT reco -- to specify name of the FK column + not null
													// constraint
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "specialization_id", nullable = false)
	private Specialization specialization;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "clinic_id", nullable = false)
	private Clinic clinic;

	private String qualifications;
	private String experience;
	private String availabilitySchedule;
	private String profilePicture;

}
