Vue.component("countdown", {
    template:'<div class="row">'
            +'    <div class="col-12 text-center">'
            +'        <div :class="classMessage">{{ message }}</div>'
            +'        <span class="fw-bold text-primary fs-1">{{ minutes }}</span>'
            +'        <span class="fw-bold text-primary fs-3 mx-3">:</span>'
            +'        <span class="fw-bold text-primary fs-3">{{ seconds }}</span>'
            +'    </div>'
            +'    <div class="col-12 text-center">'
            +'        <span class="fw-bold">Phút</span>'
            +'        <span class="mx-4"></span>'
            +'        <span class="fw-bold">Giây</span>'
            +'    </div>'
            +'</div>',
    props: {
        totalTime: {
            type: Number,
            default: 0
        }
    },
    data: function () {
        return {
            message: 'Thời gian còn lại',
            classMessage : '',
            totalTimes: this.totalTime,
            interval : null,
            timeLeft: 0
        }
    },
    computed: {
        minutes: function() {
            var min = Math.floor(this.totalTimes / 60);
            return min >= 10 ? min : '0' + min;
        },
        seconds: function() {
            var sec = this.totalTimes - (this.minutes * 60);
            return sec >= 10 ? sec : '0' + sec;
        }
    },
    watch: {
        totalTime: function(val) {
        	this.totalTimes = val;
        },
        totalTimes: function(value) {
            if (value == 0) {
                this.timerPause();
                this.message = 'Đang nộp bài...';
                this.$emit('timeout');
            } else if (value <= 30 && value > 10) {
                this.message = 'Sắp hết giờ rồi, kiểm tra lại đáp án nhé!';
                this.classMessage = 'text-warning fw-bold';
            } else if (value <= 10) {
                this.message = 'Chuẩn bị nộp bài, hệ thống sẽ tự động nộp bài khi hết giờ!';
                this.classMessage = 'text-danger fw-bold';
            }
        }
    },
    methods: {
        timerRun() {
            this.timerRunning = true;
            this.interval = setInterval(this.countdownTimer, 1000);
        },
        timerPause() {
            this.timerRunning = false;
            clearInterval(this.interval);
        },
        timerReset() {
            this.timerRunning = false;
            clearInterval( () => { this.interval; });
            this.totalTimes = (25 * 60);
        },
        timerCountdown() {
            this.timerRunning = true;
            this.interval = setInterval(this.updateCurrentTime, 1000);
            setInterval( () => {
                this.timerMinutes--
            }, 60 * 1000)
            if(this.timerSeconds === '00'){
                this.timerSeconds = 59;
                setInterval( () => {
                    this.timerSeconds--
                }, 1000);
            } else {
                setInterval( () => {
                    this.timerSeconds--
                }, 1000);
            }
        },
        countdownTimer() {
            if (this.timerRunning == true) {
                this.totalTimes--;
            }
        },
        returnTimeLeft() {
        	return this.totalTime - this.totalTimes;
        }
    }
});