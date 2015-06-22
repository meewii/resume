package com.sinfreu.marie.resume.objects;

import android.graphics.Bitmap;

public class JobXp {

	protected String companyName;
	protected String jobPosition;
	protected long startingDate;
	protected long endingDate;
	protected Bitmap companyLogo;

	public JobXp(String companyName, String jobPosition, long startingDate, long endingDate) {
		this.companyName = companyName;
		this.jobPosition = jobPosition;
		this.startingDate = startingDate;
		this.endingDate = endingDate;
	}

	public String getCompanyName() {
		return companyName;
	}
	public JobXp setCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

	public String getJobPosition() {
		return jobPosition;
	}
	public JobXp setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
		return this;
	}

	public long getStartingDate() {
		return startingDate;
	}
	public JobXp setStartingDate(long startingDate) {
		this.startingDate = startingDate;
		return this;
	}

	public long getEndingDate() {
		return endingDate;
	}
	public JobXp setEndingDate(long endingDate) {
		this.endingDate = endingDate;
		return this;
	}

	public Bitmap getCompanyLogo() {
		return companyLogo;
	}
	public JobXp setCompanyLogo(Bitmap companyLogo) {
		this.companyLogo = companyLogo;
		return this;
	}
}
