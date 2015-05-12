'use strict';

angular.module('cumulusApp')
    .factory('Accreditlab', function ($resource) {
        return $resource('api/accreditlabs/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
