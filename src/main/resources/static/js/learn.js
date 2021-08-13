var pageListData = new Vue({
    el: '#app-learn',
    data: {
        chapterId: $('#chapterId').val(),
        dataResult: '',
        resultAnswer: '',
        answerCorrect : ''
    },
    mounted() {
        this.initPage();
    },
    methods: {
        initPage(){
            $('#preloader').fadeIn();
            var _this = this;
            var config = {
                    url: "/load-page-learn", 
                    method: "POST",
                    params: {
                        chapterId : this.chapterId
                    }
                }
                axios(config).then(function (response) {
                    _this.dataResult = response.data.result;
                    _this.answerCorrect = _this.getAnswerCorrect()
                    $('#preloader').fadeOut();
                })
                .catch(function (error) {
                    console.log(error);
                    $('#preloader').fadeOut();
                });
        },
        getAnswerCorrect : function() {
            return this.dataResult.questionRandom.listAnswers.filter(function (item) {
                if (item.true) {
                    return item;
                }
            })[0].content;
        },
        submitAnswer: function(answerId){
        	$('#preloader').fadeIn();
            var _this = this;
            var config = {
                    url: "/submit-answer", 
                    method: "POST",
                    params: {
                        answerId : answerId
                    }
                }
                axios(config).then(function (response) {
                    _this.resultAnswer = response.data;
                    if (_this.resultAnswer.status == 'true') {
                        
                    } else {
                        
                    }
                    $('#preloader').fadeOut();
                })
                .catch(function (error) {
                    $('#loading').fadeOut();
                    console.log(error);
                });
        }
    }
})