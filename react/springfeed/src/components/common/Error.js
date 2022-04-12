const Error = ({ message, errorMessage }) => {
  return (
    <div>
      <div className="alert alert-danger">
        <div className="alert-header h2">에러가 났어요 😵</div>
        <p> {message} </p>

        <hr />

        <p>support@springfeed.com</p>

      </div>

      <div className="alert alert-dark">
        <div className="alert-header h2">에러 메세지</div>
        <p></p>

        <hr />

        <p>{errorMessage}</p>
      </div>

    </div>
  )
}

export default Error