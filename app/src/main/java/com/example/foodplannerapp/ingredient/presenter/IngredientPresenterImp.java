//package com.example.foodplannerapp.ingredient.presenter;
//
//import com.example.foodplannerapp.area.model.AreaResponse;
//import com.example.foodplannerapp.area.view.AreaInterface;
//import com.example.foodplannerapp.model.RecipeReposatory;
//import com.example.foodplannerapp.model.RecipeReposatoryImp;
//import com.example.foodplannerapp.network.RecipeRemoteDataSourceImp;
//
//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
//import io.reactivex.rxjava3.annotations.NonNull;
//import io.reactivex.rxjava3.core.Observable;
//import io.reactivex.rxjava3.core.Observer;
//import io.reactivex.rxjava3.disposables.Disposable;
//
//public class IngredientPresenterImp implements IngredientPresenter{
//    private AreaInterface _view;
//    private RecipeReposatory _repo;
//    RecipeRemoteDataSourceImp recipeRemoteDataSourceImp;
//
//    public IngredientPresenterImp(AreaInterface _view, RecipeReposatoryImp _repository) {
//        this._view = _view;
//        this._repo = _repository;
//    }
//
//    @Override
//    public void getIngredRecipe() {
//        Observable<AreaResponse> observable= _repo.getAreaRecipes();
//
//        observable
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<AreaResponse>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(@NonNull AreaResponse mealResponse) {
//                        _view.ShowData(mealResponse.meals);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
////                        _view.showErrMsg(e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
//}
