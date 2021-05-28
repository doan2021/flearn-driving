var pageListData = new Vue({
    el: '#app-learn',
    data: {
        chapterId: $('#chapterId').val(),
        dataResult: '',
        resultAnswer: '',
        answered: false,
        answerCorrect : ''
    },
    mounted() {
        this.initPage();
    },
    methods: {
        initPage(){
            $('#loading').fadeIn();
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
                    $('#loading').fadeOut();
                })
                .catch(function (error) {
                    console.log(error);
                    $('#loading').fadeOut();
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
            var _this = this;
            var config = {
                    url: "/submit-answer", 
                    method: "POST",
                    params: {
                        questionId : this.dataResult.questionRandom.questionId,
                        answerId : answerId
                    }
                }
                axios(config).then(function (response) {
                    _this.resultAnswer = response.data;
                    if (_this.resultAnswer.status == 'true') {
                        _this.answered = true;
                        setTimeout(function(){_this.initPage(); _this.answered = false;}, 2000)
                    } else {
                        $.alert({
                            icon: 'far fa-sad-cry',
                            theme: 'modern',
                            title: _this.resultAnswer.message,
                            content: '<h5>Answer is correct</h5><br>' + _this.answerCorrect,
                            type: 'red',
                            buttons: {
                                confirm: function () {
                                    _this.initPage();
                                }
                            }
                        });
                    }
                })
                .catch(function (error) {
                    $('#loading').fadeOut();
                    console.log(error);
                });
        }
    }
})