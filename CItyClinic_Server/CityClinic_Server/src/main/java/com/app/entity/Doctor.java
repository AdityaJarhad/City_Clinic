package com.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user; // User is now the primary key

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "specialization_id", nullable = false)
	private Specialization specialization;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "clinic_id", nullable = false)
	private Clinic clinic;

	private String qualifications;
	private String experience;
	private String availabilitySchedule;
	private String profilePicture;
}
