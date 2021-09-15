export abstract class Compte {

  constructor(
    protected _login?: string,
    protected _password?: string,
    protected _id?: number
  ){}


	public get login() : string|undefined {
		return this._login;
	}

	public set login(login : string|undefined) {
		this._login = login;
	}

	public get password() : string|undefined {
		return this._password;
	}

	public set password(password : string|undefined) {
		this._password = password;
	}

	public get id() : number | undefined {
    return this._id;
	}


	public set id(id : number|undefined) {
		this._id = id;
	}

}
