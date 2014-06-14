var module = angular.module('springBootRaceClient', []);

module.controller('mainCtrl', function($scope, $http) {

    var buildBaseUrl = function(serverIp, userPseudo) {
        return url = 'http://' + $scope.serverIp + '/' + $scope.userPseudo + '/'
    };

    $scope.startPlaying = function() {
        $scope.configOk = 0;
        $http.get(buildBaseUrl($scope.serverIp, $scope.userPseudo)
            .success(function() {
                $scope.configOk = 1;
            })
            .error(function() {
                $scope.configOk = -1;
            });
        console.log($scope.configForm, $scope.pseudo);
    }

    $scope.validateStep1 = function() {
        $scope.step1 = 0;

        $http.get(buildBaseUrl($scope.serverIp, $scope.userPseudo)
            .success(function() {
                $scope.step1 = 1;
            })
            .error(function() {
                $scope.step1 = -1;
            });
        console.log('validateStep1');
    }

    $scope.validateStep2 = function() {
        console.log('validateStep2');
    }

    $scope.validateStep3 = function() {
        console.log('validateStep3');
    }

    $scope.validateStep4 = function() {
        console.log('validateStep4');
    }
});