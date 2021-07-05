Vue.use(window.vuelidate.default);
const { required, maxLength } = window.validators
new Vue({
    el: '#register-exam-app',
    data: {
        pageContent        : [],
        listProvince       : [],
        provinceId         : '',
        listDistrict       : [],
        districtId         : '',
        listWard           : [],
        step1              : 'active',
        step2              : 'disabled',
        step3              : 'disabled',
        step4              : 'disabled',
        formUpdateInfoDrivingLicense : {
            examId             : '',
            lastName           : '',
            middleName         : '',
            firstName          : '',
            gender             : '',
            birthDay           : '',
            address1           : '',
            address2           : '',
            wardId             : '',
            email              : '',
            numberPhone        : '',
            workingUnit        : '',
            identityCardNumber : '',
            placeOfIssue       : '',
            typeDrivingLicense : '',
            issueDate          : ''
            
        }
    },
    watch: {
        districtId: function(value) {
            if (value != null && value != '') {
                this.loadWard();
            }
        }
    },
    mounted() {
        this.searchListExam();
        alert(this.$commonMethod.test);
    },
    methods: {
        searchListExam: function(pageNumber){
            $('#loading').fadeIn();
            var _this = this;
            var config = {
                url: "/search-exam", 
                method: "GET",
                params: {
                    pageNumber: pageNumber
                }
            }
            axios(config).then(function (response) {
                _this.pageContent = response.data.result.pageContent;
                _this.listProvince = response.data.result.listProvince;
                $('#loading').fadeOut();
            })
            .catch(function (error) {
                console.log(error);
                $('#loading').fadeOut();
            });
        },
        selectProvince: function() {
            if (this.provinceId != null && this.provinceId != '') {
                this.loadDistrict();
            }
        },
        loadDistrict: function() {
            $('#loading').fadeIn();
            var _this = this;
            var config = {
                url: "/load-district", 
                method: "GET",
                params: {
                	provinceId: this.provinceId
                }
            }
            axios(config).then(function (response) {
                _this.listDistrict = response.data.result.listDistrict;
                $('#loading').fadeOut();
            })
            .catch(function (error) {
                console.log(error);
                $('#loading').fadeOut();
            });
        },
        selectDistrict: function() {
            if (this.districtId != null && this.districtId != '') {
                this.loadWard();
            } else {
            	this.listWard = '';
            }
        },
        loadWard: function() {
            $('#loading').fadeIn();
            var _this = this;
            var config = {
                url: "/load-ward", 
                method: "GET",
                params: {
                	districtId: this.districtId
                }
            }
            axios(config).then(function (response) {
                _this.listWard = response.data.result.listWard;
                $('#loading').fadeOut();
            })
            .catch(function (error) {
                console.log(error);
                $('#loading').fadeOut();
            });
        },
        formatDate: function(value) {
            return moment(value).format('MM/DD/YYYY')
        },
        displayValue: function(value) {
            if (value == null || value == '') {
                return '-';
            } else {
                return value;
            }
        },
        displayCurrency: function(value){
            if (value == null || value == '') {
                return 'Chưa công bố';
            } else {
                return value.toLocaleString() + ' VNĐ';
            }
        },
        selectExam: function(examId, typeDrivingLicense) {
            this.formUpdateInfoDrivingLicense.examId = examId;
            this.step1 = 'complete';
            this.step2 = 'active';
            this.formUpdateInfoDrivingLicense.typeDrivingLicense = typeDrivingLicense;
        },
        backStep1: function(){
            this.formUpdateInfoDrivingLicense.examId = '';
            this.step1 = 'active';
            this.step2 = 'disabled';
            this.formUpdateInfoDrivingLicense.typeDrivingLicense = '';
        },
        nextStep3: function(){
            
        }
    },
    validations: {
        formUpdateInfoDrivingLicense : {
            lastName: {
                required,
                maxLength: maxLength(36)
            },
            middleName: {
                maxLength: maxLength(255)
            },
            firstName: {
                required,
                maxLength: maxLength(36)
            },
            gender  : {
                required
            },
            birthDay: {
                required,
                maxLength: maxLength(36)
            },
            address1: {
                required,
                maxLength: maxLength(255)
            },
            address2: {
                maxLength: maxLength(255)
            },
            wardId  : {
                required,
                maxLength: maxLength(36)
            },
            email   : {
                required,
                maxLength: maxLength(255)
            },
            numberPhone: {
                required,
                maxLength: maxLength(10)
            },
            workingUnit : {
                required,
                maxLength: maxLength(10)
            },
            identityCardNumber: {
                required,
                maxLength: maxLength(11)
            },
            placeOfIssue      : {
                required,
                maxLength: maxLength(10)
            },
            issueDate: {
                required,
                maxLength: maxLength(10)
            }
        }
    }
});