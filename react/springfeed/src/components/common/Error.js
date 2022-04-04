const Error = ({ errorMessage }) => {
  return (
    <div>
      <div className="alert alert-danger">
        <div className="alert-header h2">에러가 났어요 😵</div>
        <p>걱정마세요. 아마 서버 오류일거에요. 급하시면 아래 이메일로 개발자에게 연락해 주세요 📞 </p>

        <hr />

        <p>support@springfeed.com</p>

      </div>

      <div className="alert alert-dark">
        <div className="alert-header h2">에러 메세지에요 😱</div>
        <p>혹시 문의하실 생각이시라면 아래의 에러 메세지도 같이 보내주세요. 개발자들의 야근을 줄일 수 있어요.</p>

        <hr />

        <p>{errorMessage}</p>
      </div>

    </div>
  )
}

export default Error