var module = angular.module('springBootRaceServer', []);

module.controller('mainCtrl', function($scope, $http) {

    $scope.gameStatus = [];
    $scope.onGameStatusUpdated = function(data) {

    _.forEach(data.userGameStatus, function(userGame) {
        userGame.nbOfValidatedStep = userGame.stepInfos.length;

        userGame.validatedSteps = _.chain(userGame.stepInfos)
                                       .map(function(stepInfo) {
                                           return stepInfo.step.replace('_', '');
                                       })
                                       .sortBy()
                                       .value()
                                       .join();

            userGame.chrono = _.chain(userGame.stepInfos)
                             .map(function(stepInfo) {
                                 return moment(stepInfo.instant.epochSecond * 1000).toDate();
                             })
                             .max()
                             .value();

            userGame.chronoString = moment(userGame.chrono).format('hh:mm:ss DD/MM/YYYY');
        });

        $scope.gameStatus = data.userGameStatus;
        $(".table").animate({
            backgroundColor: "yellow"
        }).animate({
            backgroundColor: "white"
        });
    };

    var stompClient = null;
    var webSocketConnect = function connect() {
        var socket = new SockJS('/gameStatus');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            stompClient.subscribe('/topic/gameStatus', function(message){
                $scope.$apply(function() {
                    $scope.onGameStatusUpdated(JSON.parse(message.body));
                });
            });

            $http.get('/gameStatus');
        });
    };

    webSocketConnect();
});