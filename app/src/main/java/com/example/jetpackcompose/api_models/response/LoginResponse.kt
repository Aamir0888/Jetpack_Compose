package com.example.jetpackcompose.api_models.response

data class LoginResponse(
    val code: Int,
    val error: Any,
    val message: String,
    val result: Result,
    val token: String
) {
    data class Result(
        val accountHolderName: String,
        val accountNumber: String,
        val age: String,
        val bankName: String,
        val branchAddress: String,
        val companyName: String,
        val countryCode: String,
        val currentAddress: String,
        val dateOfJoining: String,
        val degreeEducation: String,
        val department: String,
        val designation: String,
        val designationId: Int,
        val deviceAddress: String,
        val deviceName: String,
        val digitalMarketHeadName: String,
        val digitalMarketingHeadId: Int,
        val dob: String,
        val email: String,
        val emailMarketManagerId: Int,
        val emailMarketManagerName: Any,
        val employeeId: String,
        val employeePassword: Any,
        val esicNumber: Any,
        val firstName: String,
        val gender: String,
        val highSchool: String,
        val hrEmail: Any,
        val hrHeadId: Int,
        val hrHeadName: String,
        val hrId: Int,
        val id: Int,
        val ifscCode: String,
        val intermediate: String,
        val isHr: Int,
        val isManager: Int,
        val isTeamLead: Int,
        val isTermsAccepted: Int,
        val isUpper: Int,
        val itSupportEngId: Int,
        val itSupportEngName: String,
        val lastName: String,
        val level: String,
        val managerId: Int,
        val managerName: String,
        val maritalStatus: String,
        val mobileNumber: String,
        val nationality: String,
        val organizationId: Int,
        val pfNumber: Any,
        val profilePicturePath: String,
        val qaManagerId: Int,
        val qaManagerName: Any,
        val salesHeadId: Int,
        val salesHeadName: String,
        val salesManagerId: Int,
        val salesManagerName: String,
        val shiftDetails: String,
        val shiftEndTime: String,
        val shiftStartTime: String,
        val skypeId: String,
        val socialMediaMarketManagerId: Int,
        val socialMediaMarketManagerName: String,
        val teamLeadId: Int,
        val teamLeadName: String,
        val technologies: List<Technology>,
        val technology: Any,
        val totalExperience: Double,
        val totalMonthExperience: Int,
        val totalYearsExperience: Int,
        val type: String,
        val uanNumber: Any,
        val uiuxManagerId: Int,
        val uiuxManagerName: Any,
        val uiuxTeamLeadId: Int,
        val uiuxTeamLeadName: Any,
        val userName: String,
        val workAddress: String
    ) {
        data class Technology(
            val id: Int,
            val technologyName: String
        )
    }
}