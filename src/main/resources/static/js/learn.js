var pageListData = new Vue({
    el: '#app-learn',
    data: {
        chapterId: $('#chapterId').val(),
        dataResult: '',
        currentQuestion : '',
        listAnswer      : [],
        resultAnswer: '',
        answerIdSelect: ''
    },
    mounted() {
    	var _this = this;
    	window.onkeydown = function() {
    		if (_this.resultAnswer != null && _this.resultAnswer != '' && !_this.resultAnswer.result.answerTrue) {
    			_this.initPage();
    		}
    	}
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
                    _this.currentQuestion = response.data.result.questionRandom;
                    _this.listAnswer = response.data.result.listAnswer;
                    _this.resultAnswer = '',
                    _this.answerIdSelect = ''
                    $('#preloader').fadeOut();
                })
                .catch(function (error) {
                    console.log(error);
                    $('#preloader').fadeOut();
                });
        },
        submitAnswer: function(answerId){
        	$('#preloader').fadeIn();
        	this.answerIdSelect = answerId;
            var _this = this;
            var config = {
                    url: "/submit-answer", 
                    method: "POST",
                    params: {
                        answerId : this.answerIdSelect
                    }
                }
                axios(config).then(function (response) {
                    _this.resultAnswer = response.data;
                    $('#preloader').fadeOut();
                    if (_this.resultAnswer.result.answerTrue) {
                        setTimeout(function(){
                        	_this.initPage();
                        }, 2000);
                    }
                })
                .catch(function (error) {
                    $('#preloader').fadeOut();
                    console.log(error);
                });
        }
    }
})