var videoComponent = {
  template: '<h1>Video content</h1><video ref="myVideoEl" :srcObject ="myStreamSrc" autoplay="autoplay">',
  props: ['myStreamSrc']
}
Vue.component("video-component", videoComponent)

Vue.use(window.vuelidate.default);
const { required, maxLength, email } = window.validators
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
            issueDate          : '',
        },
        term               : false,
        errorTerm          : false,
        formUploadDocument : {
            identityCardImageFront: [],
            identityCardImageBack : [],
            imagePortrait: [],
            healthCertificationFile : [],
        },
        identityCardImageFrontSrc : '',
        identityCardImageBackSrc : '',
        constraints: {
                  audio: false,
                  video: {
                      height: 400
                  }
                },
        localstream: [],
        srcVideoTag: '',
        imageCapturer : '',
        imagePortraitSrc: '',
        cameraOn : false,
        errorCamera : false,
        message : ''
    },
    watch: {
        term: function(value) {
            if (value) {
                this.errorTerm = false;
            } else {
                this.errorTerm = true;
            }
        }
    },
    mounted() {
        this.searchListExam();
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
            } else {
                this.listDistrict = [];
            }
            this.districtId = '';
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
            this.formUpdateInfoDrivingLicense.wardId = '';
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
            if (this.$v.formUpdateInfoDrivingLicense.$invalid) {
                this.$v.formUpdateInfoDrivingLicense.$touch();
            } else {
                if (!this.term) {
                    this.errorTerm = true;
                } else {
                    this.step2 = 'complete';
                    this.step3 = 'active';
                }
            }
        },
        backStep2: function() {
            this.step2 = 'active';
            this.step3 = 'disabled';
        },
        nextStep4: function() {
            if (this.$v.formUploadDocument.$invalid) {
                this.$v.formUploadDocument.$touch();
            } else {
                $('#loading').fadeIn();
                var _this = this;
                var formData = new FormData();
                formData.append('examId', this.formUpdateInfoDrivingLicense.examId);
                formData.append('lastName', this.formUpdateInfoDrivingLicense.lastName);
                formData.append('middleName', this.formUpdateInfoDrivingLicense.middleName);
                formData.append('firstName', this.formUpdateInfoDrivingLicense.firstName);
                formData.append('gender', this.formUpdateInfoDrivingLicense.gender);
                formData.append('birthDay', this.formUpdateInfoDrivingLicense.birthDay);
                formData.append('address1', this.formUpdateInfoDrivingLicense.address1);
                formData.append('address2', this.formUpdateInfoDrivingLicense.address2);
                formData.append('wardId', this.formUpdateInfoDrivingLicense.wardId);
                formData.append('email', this.formUpdateInfoDrivingLicense.email);
                formData.append('numberPhone', this.formUpdateInfoDrivingLicense.numberPhone);
                formData.append('workingUnit', this.formUpdateInfoDrivingLicense.workingUnit);
                formData.append('identityCardNumber', this.formUpdateInfoDrivingLicense.identityCardNumber);
                formData.append('placeOfIssue', this.formUpdateInfoDrivingLicense.placeOfIssue);
                formData.append('typeDrivingLicense', this.formUpdateInfoDrivingLicense.typeDrivingLicense);
                formData.append('issueDate',               this.formUpdateInfoDrivingLicense.issueDate);
                formData.append('identityCardImageFront',  this.formUploadDocument.identityCardImageFront);
                formData.append('identityCardImageBack',   this.formUploadDocument.identityCardImageBack);
                formData.append('imagePortrait',           this.formUploadDocument.imagePortrait);
                formData.append('healthCertificationFile', this.formUploadDocument.healthCertificationFile);
                axios.post("/register-exam", formData, { headers : { 'Content-Type': 'multipart/form-data' }
                }).then(function (respone) {
                    _this.message = respone.data.result.message;
                    if (respone.data.result.status == 'success') {
                        _this.step3 = 'complete';
                        _this.step4 = 'active';
                    } else {
                        alert(_this.message);
                    }
                    $('#loading').fadeOut();
                }).catch(function () {
                    $('#loading').fadeOut();
                });
            }
        },
        loadIdentityCardFront: function(e){
            if (e.target.files != null && e.target.files.length != 0) {
                this.formUploadDocument.identityCardImageFront = e.target.files[0];
                this.identityCardImageFrontSrc = URL.createObjectURL(this.formUploadDocument.identityCardImageFront);
            }
        },
        loadIdentityCardBack: function(e){
            if (e.target.files != null && e.target.files.length != 0) {
                this.formUploadDocument.identityCardImageBack = e.target.files[0];
                this.identityCardImageBackSrc = URL.createObjectURL(this.formUploadDocument.identityCardImageBack);
            }
        },
        start: function() {
            _this = this;
            navigator.mediaDevices.getUserMedia(this.constraints)
            .then(function(mediastream){
                _this.localstream = mediastream;
                _this.imageCapturer = new ImageCapture(_this.localstream.getVideoTracks()[0]);
                _this.cameraOn = true;
                _this.errorCamera = false;
            })
            .catch(function(){
                _this.errorCamera = true;
            });
        },
        end: function() {
            this.localstream.getTracks()[0].stop();
            this.cameraOn = false;
        },
        takePhoto: function () {
            _this = this;
            this.imageCapturer.takePhoto().then(function(blob){
                _this.formUploadDocument.imagePortrait = blob;
                _this.imagePortraitSrc = URL.createObjectURL(blob);
            });
        },
        removePhoto: function () {
            this.formUploadDocument.imagePortrait = [];
            this.imagePortraitSrc = '';
        },
        uploadHealthCertificationFile: function(e){
            if (e.target.files != null && e.target.files.length != 0) {
                this.formUploadDocument.healthCertificationFile = e.target.files[0];
            }
        },
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
                maxLength: maxLength(10)
            },
            address1: {
                required,
                maxLength: maxLength(255)
            },
            address2: {
                maxLength: maxLength(255)
            },
            wardId  : {
                required
            },
            email   : {
                required,
                email,
                maxLength: maxLength(255)
            },
            numberPhone: {
                required,
                maxLength: maxLength(10)
            },
            workingUnit : {
                required,
                maxLength: maxLength(255)
            },
            identityCardNumber: {
                required,
                maxLength: maxLength(11)
            },
            placeOfIssue      : {
                required
            },
            issueDate: {
                required,
                maxLength: maxLength(10)
            }
        },
        formUploadDocument : {
            identityCardImageFront: {
                required
            },
            identityCardImageBack : {
                required
            },
            imagePortrait: {
                required
            },
            healthCertificationFile : {
                required
            }
        }
    }
});