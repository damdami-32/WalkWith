public calss LoginController implements Controller {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        
        try {
            //모델에 login 처리 위임
            UserManager.getInstance().login(userId, password);
            
            //DB에 userId 저장(어케함?
            
            return "redirect:/user/list"; //사용자 목록 요청으로 redirect
        } catch (Exception e) {
            request.setAttribute("loginFailed", true);
            request.setAttribute("exception", e);
            return "/user/loginForm.jsp"; //loginForm View로 forward
        }
    }
}