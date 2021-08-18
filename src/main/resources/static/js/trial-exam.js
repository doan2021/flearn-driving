new Vue({
    el: '#app-trial-exam',
    data: {
        examQuestionsId : $('#examQuestionsId').val(),
        examMinutes : 0 ,
        examQuestions : '',
        listQuestions : [],
        questionCurrentIndex : 0,
        listSelectAnswers : [],
        questionRest : 0,
        confirmSubmit: false,
        isComplete : false,
        isPass : false
    },
    watch: {
        listSelectAnswers: function() {
            this.questionRest = this.listSelectAnswers.length - this.listSelectAnswers.filter(function(item) {
                return (item != null && item != '');
            }).length;
            this.confirmSubmit = false;
        }
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
                    _this.questionRest = _this.listQuestions.length;
                    _this.confirmSubmit = false;
                    _this.$refs.countdown.timerRun();
                    _this.isComplete = false;
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
            if (this.questionRest > 0) {
                this.confirmSubmit = true;
            } else {
                this.postAnswer();
            }
        },
        postAnswer: function() {
            $('#preloader').fadeIn();
            var _this = this;
            var formData = new FormData();
            formData.append('listSelectAnswers', this.listSelectAnswers.filter(function(item) {
                return (item != null && item != '');
            }));
            formData.append('examQuestionsId', this.examQuestionsId);
            formData.append('timeLeft', this.$refs.countdown.timeLeft);
            axios.post("/post-answer", formData, { headers : { 'Content-Type': 'application/json' }
            }).then(function (respone) {
            	$('#preloader').fadeOut();
            	_this.isPass = respone.data.result.pass;
            	_this.isComplete = true;
            });

        },
        continueAnswer: function () {
            this.confirmSubmit = false;
        }
    }
})