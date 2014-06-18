var module = angular.module('springBootRaceClient', ['ui.bootstrap']);

module.controller('mainCtrl', function($scope, $http) {


    $scope.stepValidatedMessage = 'OK étape validée';
    $scope.stepNotValidatedMessage = 'Etape non validée';

    $scope.userPseudo = 'kebuu';
    $scope.serverIp = 'localhost';
    $scope.serverPort = '8182';
    $scope.userHostAndPort = 'localhost:8181';
    $scope.step2Secret = 'Un message secret pour le step 2';
    $scope.step3Secret = 'Un message secret pour le step 3';
    $scope.step5Secret = 'b47fb25';

    var buildBaseUrl = function() {
        return url = 'http://' + $scope.serverIp + ':' + $scope.serverPort + '/' + $scope.userPseudo + '/';
    };

    var validateStep = function(url, scopeFlagName, params) {
        $scope[scopeFlagName] = 0;
        $http.get(url, {
            params: params
        })
       .success(function() {
           $scope[scopeFlagName] = 1;
       })
       .error(function() {
           $scope[scopeFlagName] = -1;
       });
    };

    $scope.startPlaying = function() {
        validateStep(buildBaseUrl() + 'start', 'configOk');
    }

    $scope.validateStep1 = function() {
        var params = {
            userHostAndPort: $scope.userHostAndPort
        };
        validateStep(buildBaseUrl() + 'validateStep1', 'step1', params);
    }

    $scope.validateStep2 = function() {
        var params = {
            secret: $scope.step2Secret
        };
        validateStep(buildBaseUrl() + 'validateStep2', 'step2', params);
    }

    $scope.validateStep3 = function() {
        var params = {
            secret: $scope.step3Secret
        };
        validateStep(buildBaseUrl() + 'validateStep3', 'step3', params);
    }

    $scope.validateStep4 = function() {
        var params = {
            userHostAndPort: $scope.userHostAndPort
        };
        validateStep(buildBaseUrl() + 'validateStep4', 'step4', params);
    }

    $scope.validateStep5 = function() {
        var params = {
            secret: $scope.step5Secret
        };
        validateStep(buildBaseUrl() + 'validateStep5', 'step5', params);
    }
});