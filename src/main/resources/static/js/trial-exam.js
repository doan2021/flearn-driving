new Vue({
    el: '#app-trial-exam',
    data: {
    	examQuestionsId : $('#examQuestionsId').val(),
        examMinutes : 0 ,
        examQuestions : '',
        listQuestions : [],
        questionCurrentIndex : 0,
        listSelectAnswers : []
    },
    mounted: function() {
    	this.initPage();
    },
    methods: {
        initPage(){
            $('#preloader').fadeIn();
            var _this = this;
            var config = {
                    url: "/init-trial-exam", 
                    method: "GET",
                    params: {
                        examQuestionsId : this.examQuestionsId
                    }
                }
                axios(config).then(function (response) {
                	_this.listQuestions = response.data.result.listQuestions;
                	_this.examQuestions = response.data.result.examQuestions;
                	_this.examMinutes = _this.examQuestions.drivingLicense.examMinutes;
                	_this.questionCurrentIndex = 0;
                	_this.listSelectAnswers.length = _this.listQuestions.length;
                	_this.$refs.countdown.timerRun();
                    $('#preloader').fadeOut();
                })
                .catch(function (error) {
                    $('#preloader').fadeOut();
                });
        },
        selectAnswer: function(answerId) {
        	this.$set(this.listSelectAnswers, this.questionCurrentIndex, answerId)
        },
        nextQuestion: function() {
        	this.questionCurrentIndex++;
        },
        previousQuestion: function() {
        	this.questionCurrentIndex--;
        },
        selectQuestion: function(index) {
        	this.questionCurrentIndex = index;
        },
        submitAnswer: function() {
        	
        }
    }
})