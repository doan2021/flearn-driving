var pageSelectExamQuestion = new Vue({
    el: '#app-select-exam-question',
    data: {
        listDrivingLicense : [],
        drivingLicenseCurrent : '',
        listExamQuestions  : [],
        examQuestionsIdCurrent  : ''
    },
    mounted() {
        this.initPage();
    },
    methods: {
        initPage: function(){
            $('#preloader').fadeIn();
            var _this = this;
            var config = {
                    url: "/init-select-exam-question", 
                    method: "GET"
                }
            axios(config).then(function (response) {
                _this.listDrivingLicense = response.data.result.listDrivingLicense;
                _this.selectDrivingLicense(_this.listDrivingLicense[0]);
                $('#preloader').fadeOut();
            })
            .catch(function (error) {
                console.log(error);
                $('#preloader').fadeOut();
            });
        },
        selectDrivingLicense: function(drivingLicense){
            $('#preloader').fadeIn();
            this.drivingLicenseCurrent = drivingLicense;
            var _this = this;
            var config = {
                    url: "/select-driving-license", 
                    method: "GET",
                    params: {
                    	drivingLicenseId: drivingLicense.drivingLicenseId
                    }
                }
            axios(config).then(function (response) {
                _this.listExamQuestions = response.data.result.listExamQuestions;
                _this.examQuestionsIdCurrent = '';
                $('#preloader').fadeOut();
            })
            .catch(function (error) {
                console.log(error);
                $('#preloader').fadeOut();
            });
        },
        selectExamQuestion: function(examQuestionsId) {
        	this.examQuestionsIdCurrent = examQuestionsId;
        }
    }
})