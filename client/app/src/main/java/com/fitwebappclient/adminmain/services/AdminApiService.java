package com.fitwebappclient.adminmain.services;


public class AdminApiService {


    public AdminApiService{}


    public void createNewCourseOnServer(CourseDao courseDao, MutableLiveData<ServerResponse> serverResponseStatus) {
        Call<ServerResponse> call = adminApiServer.createCourseOnServer(courseDao, "Bearer " + tokenJWN.getToken());
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {
                if (response.code() == 200) {
                    serverResponseStatus.setValue(response.body());
                } else {
                    serverResponseStatus.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                System.out.println("fail" + t.getMessage());
                serverResponseStatus.setValue(null);
            }
        });
    }
}
